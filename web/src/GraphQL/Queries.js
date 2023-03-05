import { gql } from "@apollo/client";

export function LOAD_LINKS(id) {
  if (id != 1 && id != 2) {
    id = 1;
  }
  console.log(id);

  return gql`
query{
    piece(id: "${id}") {
      id
      name
      link
      duration    
    }
  }
`;
}
