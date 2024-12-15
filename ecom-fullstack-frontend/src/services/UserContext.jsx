import { createContext, useContext, useState, useEffect } from "react";
import axios from "axios";

const UserContext = createContext();

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState("");

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/auth/current-user", {
                    withCredentials: true,
                });
                console.log("User fetched:", response.data);
                setUser(response.data);
            } catch (error) {
                console.log("No user is logged in.");
            }
        };
        fetchUser();
    },[]);

    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
};

export const useUser = () => useContext(UserContext);

