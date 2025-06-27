import {useState} from "react";
import axios from "axios";

export default function PersonForm({onAdd}){
    const[name, setName] = useState("");

    const handleSubmit = async(e)=>{
        e.preventDefault();
        const res = await axios.post("http://localhost:8081/api/v1/person",{
            name,
            });
        onAdd(res.data);
        setName("");
        };

    return(
        <form onSubmit={handleSubmit}>
            <input
                placeholder="Person Name"
                value={name}
                onChange={(e)=> setName(e.target.value)}
                required
            />
            <button type="Submit">Add Person</button>
            </form>
        );

    }