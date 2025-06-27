import React, { useEffect, useState } from "react";
import axios from "axios";

const MutualFriendsForm=()=>{
    const [people, setPeople] = useState([]);
    const [person1, setPerson1] = useState("");
    const [person2, setPerson2] = useState("");
    const [mutualFriends, setMutualFriends] = useState([]);
    const [message, setMessage] = useState("");

    useEffect(()=>{
        axios.get("http://localhost:8081/api/v1/persons")
        .then((response)=> setPeople(response.data))
        .catch((error)=> console.error("Error fetching people:", error));
        }, []);

    const handleFindMutualFriend=()=>{
        axios
          .get(`http://localhost:8081/api/v1/mutualfriends/${person1}/${person2}`)
          .then((response) => {
            setMutualFriends(response.data);
            if (response.data.length === 0) {
              setMessage("No mutual friends found.");
            } else {
              setMessage("");
            }
          })
          .catch((error) => {
            console.error("Error getting and displaying mutual friends:", error);
            setMessage("Failed to retrieve mutual friends.");
          });
        };

    return(
        <div>
            <h3>Find Mutual Friends</h3>
            <select value={person1} onChange={(e)=> setPerson1(e.target.value)}>
                <option value="">Select Person 1</option>
                {people.map((person)=>(
                    <option key={person.id} value={person.id}>{person.name}</option>
                ))}
            </select>

            <select value={person2} onChange={(e)=>setPerson2(e.target.value)}>
                <option value="">Select Person 2</option>
                {people.map((person)=>(
                    <option key={person.id} value={person.id}>{person.name}</option>
                ))}
            </select>

            <button onClick={handleFindMutualFriend} disabled={!person1 || !person2}>Show Mutual Friends</button>

            <div>
                    {mutualFriends.length > 0 ? (
                      mutualFriends.map((friend) => (
                        <p key={friend.id}>{friend.name}</p>
                      ))
                    ) : (
                      message && <p>{message}</p>
                    )}
                  </div>
            </div>

        );

    };

export default MutualFriendsForm;