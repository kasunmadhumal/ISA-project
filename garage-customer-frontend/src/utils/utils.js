import axios from "axios";
import env from "react-dotenv";

export const refreshToken = async () => {

    const baseUrl = env.REACT_APP_API_BASE_URL;

    try {
      const response = await axios.post(`${baseUrl}/api/v1/auth/refresh-token`, {
      });
  
      if (response.status === 200) {
        const newToken = response.data.access_token;
        axios.defaults.headers.common['Authorization'] = `Bearer ${newToken}`;
        return newToken;
      } else {
        console.error('Token refresh failed:');
      }
    } catch (error) {
      console.error('Error refreshing token:', error);
    }
  
    return null; 
  };
  