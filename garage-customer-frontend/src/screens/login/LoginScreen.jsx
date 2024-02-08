import React from 'react';
import { Button, Checkbox, Form, Input } from 'antd';
import './LoginScreen.css';
import LockImage from '../../assets/images/lockPng.png';
import { Link } from 'react-router-dom';
import { OnFinish, OnFinishFailed } from './LoginScreenService';



const LoginScreen = () => (
    <>
        <div className="login-container">
            <div>
                <img src={LockImage} alt="logo" className="title-image" />
            </div>
            <div className='page-title'>
                <h1 className='title-text'>Sign in</h1>
            </div>
            <div className="form-container">
            <Form
                    name="basic"
                    labelCol={{
                    span: 8,
                    }}
                    wrapperCol={{
                    span: 16,
                    }}
                    style={{
                        maxWidth: 1000,
                        width: '100%',
                        marginTop: '10%',
                        marginRight: '20%',
                    }}
                    initialValues={{
                    remember: true,
                    }}
                    onFinish={OnFinish}
                    onFinishFailed={OnFinishFailed}
                    autoComplete="off"
                >
                    <Form.Item
                    label="Username"
                    name="username"
                    rules={[
                        {
                        required: true,
                        message: 'Please input your username!',
                        },
                        {
                        type: 'email',
                        message: 'Please input a valid email!',
                        },
                        {
                        max: 30,
                        message: 'Username must be at most 30 characters long!',
                        },
                        {
                        whitespace: false,
                        message: 'Username must not contain any whitespace!',
                        }
                    ]}
                    >
                    <Input
                    placeholder='email' />
                    </Form.Item>

                    <Form.Item
                    label="Password"
                    name="password"
                    rules={[
                        {
                        required: true,
                        message: 'Please input your password!',
                        },
                        {
                        whitespace: false,
                        message: 'Password must not contain any whitespace!',
                        }
                    ]}
                    >
                    <Input.Password 
                    placeholder='password'/>
                    </Form.Item>

                    <Form.Item
                    name="remember"
                    valuePropName="checked"
                    wrapperCol={{
                        offset: 8,
                        span: 16,
                    }}
                    style={{marginLeft: '30%'}}
                    >
                    <Checkbox>Remember me</Checkbox>
                    </Form.Item>

                    <Form.Item
                    style={{
                        marginLeft: '48%',
                    }}>
                    <Link to="/signup">
                        Don't have an account? Register
                    </Link>
                    </Form.Item>
                    <Form.Item
                    wrapperCol={{
                        offset: 8,
                        span: 16,
                    }}
                    style={{marginLeft: '35%'}}
                    >
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    </>

);

export default LoginScreen;