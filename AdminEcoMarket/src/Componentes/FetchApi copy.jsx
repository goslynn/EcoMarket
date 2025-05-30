import axios from "axios";
import { useEffect, useState } from "react";

export function useFetch(url) {
  const [error, setError] = useState("");
  const [data, setData] = useState([]);
  const [final, setFinal] = useState(false);

  useEffect(() => {
    axios.get(url)
      .then(res => setData(res.data))
      .catch(error => setError(error.message))
      .finally(() => setFinal(true));
  }, [url]);

  return { data, final, error };
}    