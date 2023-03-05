import React, { useState } from "react";
import { GetLink } from "../Component/GetLink";

export function Home() {
  const [inputNumber, setInputnumber] = useState(0);
  const [finalNumber, setfinalNumber] = useState(0);
  const [link, setlink] = useState("");
  const [show, setshow] = useState(false);

  function getinputNumber(e) {
    setInputnumber(e.target.value);
  }
  function handleClick() {
    setshow(true);
    setfinalNumber(inputNumber);
  }
  return (
    <div>
      <input type={"number"} onChange={getinputNumber}></input>
      <button type="submit" onClick={handleClick}>
        show
      </button>
      {/* <div>{link && link}</div> */}
      {show && <GetLink id={finalNumber} />}
    </div>
  );
}
