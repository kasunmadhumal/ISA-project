import axios from 'axios';
import env from "react-dotenv";



export const OnFinish = (values) => {
    
    console.log('Success:', values);
    console.log('Success:', env.REACT_APP_API_BASE_URL);
    validateLogin(values.username, values.password);
};

export const OnFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
};

export const validateLogin = (username, password) => {

   const baseUrl = env.REACT_APP_API_BASE_URL;
   
    axios.post(`${baseUrl}/api/v1/auth/authenticate`,{
        "email": username,
        "password": password
    } )
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
};
