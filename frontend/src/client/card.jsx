import React from "react";


const Card = (props) =>{

    let label = props.name;
    
    let imageClasses = "card-image";

    let desc = props.description
    
    if(props.quantity){
        label += " ("+props.quantity+")"
    } else {
        imageClasses += " disabled"
    }

    let divClasses = "card " + props.rarity; 

    return(
        <div className={divClasses}>
            <h3 className="card-label">{label}</h3>
            <h4 className="card-label2">Description: {desc} </h4>
            <p>y</p>
            <img className={imageClasses} src={"/api/cards/imgs/"+props.imageId}/>
        </div>
    );
};

export default Card;