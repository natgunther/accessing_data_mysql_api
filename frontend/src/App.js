
import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";
import PersonForm from "./components/PersonForm";
import PersonList from "./components/PersonList";
import AddFriendForm from "./components/AddFriendForm";
import MutualFriendsForm from "./components/MutualFriendsForm";

export default function App(){
    const[people, setPeople] = useState([]);
    const[selectedPerson, setSelectedPerson] = useState(null);
    const[selectedFriend, setSelectedFriend] = useState(null);
    const[mutualFriends, setMutualFriends] = useState([]);
    const[message, setMessage] = useState("");

    useEffect(()=>{
        axios.get("http://localhost:8081/api/v1/persons").then((res)=>{
            setPeople(res.data);
        });
    }, []);

    const handleAddPerson=(newPerson)=>{
        setPeople((prev)=> [...prev, newPerson]);
    };

    const handleFriendAdded=(updatedPerson)=>{
        alert(`${updatedPerson.name} now has a new friend!`);
    }

    const handleShowMutualFriends = (personId1, personId2)=>{
        axios
        .get(`http://localhost:8081/api/v1/mutualfriends/${personId1}/${personId2}`)
        .then((res)=>{
            const mutuals=res.data;
            setMutualFriends(mutuals);

            if(mutuals.length===0){
                setMessage("These people have no mutual friends");
            } else {
                setMessage("");
            }


        })
        .catch((err)=>{
            console.log("Error getting mutual friends:", err);
            setMessage("Failed to get mutual friends");
        });
    };


    return(
        <div>
            <h2>Add New Person</h2>
            <PersonForm onAdd={handleAddPerson} />

            <h2>Select Person</h2>
            <PersonList
                people={people}
                selected={selectedPerson}
                onSelect={setSelectedPerson}
            />

            <h2>Select Friend</h2>
            <PersonList
                people={people}
                selected={selectedFriend}
                onSelect={setSelectedFriend}
            />

            <AddFriendForm
                person1={selectedPerson}
                person2={selectedFriend}
                onAdd={handleFriendAdded}
            />

            <h2>Show Mutual Friends</h2>
            <MutualFriendsForm
                person1={selectedPerson}
                person2={selectedFriend}
                onShowMutual={()=>handleShowMutualFriends(selectedPerson?.id, selectedFriend?.id)}
                mutualFriends={mutualFriends}
                message={message}
            />

        </div>
    );
}


