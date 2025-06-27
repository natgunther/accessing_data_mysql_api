

export default function PersonList({people, selected, onSelect}){
    return(
        <ul>
            {people.map((p)=>(
                <li
                key={p.id}
                onClick={()=>onSelect(p)}
                style={{
                    cursor: "pointer",
                    fontWeight: selected?.id===p.id ? "bold":"normal",
                    }}
                >
                {p.name}
                </li>
                ))}
            </ul>
        );
    }