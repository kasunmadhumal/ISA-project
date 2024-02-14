import axios from 'axios';
import env from "react-dotenv";
import  secureLocalStorage  from  "react-secure-storage"


const api = axios.create({
  baseURL: env.REACT_APP_API_BASE_URL,
});

api.interceptors.request.use(
  (config) => {
    const token = secureLocalStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    // Handle errors (e.g., refresh token if token is expired)
    console.error('Error in interceptor:', error);
    return Promise.reject(error);
  }
);

export default api;
