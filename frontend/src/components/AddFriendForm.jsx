import axios from "axios";

export default function AddFriendForm({person1, person2, onAdd}){
    const handleAdd=async()=>{
        if(person1 && person2){
            const res = await axios.post("http://localhost:8081/api/v1/addfriend", null, {
                params: {
                    personId1: person1.id,
                    personId2: person2.id
                    }
                });
            onAdd(res.data);
            }
        };

    return (
        <div>
            <button onClick={handleAdd} disabled={!person1 || !person2}>
                Add {person2?.name} as a friend to {person1?.name}
            </button>
            </div>
        );
    }