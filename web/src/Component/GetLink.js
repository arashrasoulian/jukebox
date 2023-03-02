import React, { useEffect, useState } from "react";
import { useQuery, gql } from "@apollo/client";
import { LOAD_LINKS } from "../GraphQL/Queries";
import { Embedlink } from "./Getembed";

export function GetLink({ id }) {
  const { error, loading, data } = useQuery(LOAD_LINKS(id));
  const [users, setUsers] = useState([]);
  const [showembed, setShowembed] = useState(false);
  useEffect(() => {
    if (data) {
      setUsers(data);
    }
  }, [data]);
  function handleClick() {
    setShowembed(true);
  }

  return (
    <div>
      {data && <div> {data.piece.link}</div>}
      <button type="submit" onClick={handleClick}>
        Show
      </button>
      {showembed && data && (
        <div>
          <iframe src={`${Embedlink(data.piece.link)}`}></iframe>
        </div>
      )}
    </div>
  );
}
