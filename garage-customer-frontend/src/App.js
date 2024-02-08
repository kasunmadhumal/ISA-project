import React from 'react';
import LoginScreen from './screens/login/LoginScreen';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import SignupScreen from './screens/signup/SignupScreen';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginScreen />} />
        <Route path="/signup" element={<SignupScreen />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
