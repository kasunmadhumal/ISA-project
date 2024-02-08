import React from 'react';
import { Button, Form, Input } from 'antd';
import './SignupScreen.css';
import LockImage from '../../assets/images/lockPng.png';
import { Link } from 'react-router-dom';


const onFinish = (values) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};

const SignupScreen = () => (
    <>     
        <div className="signup-container">
            <div>
                <img src={LockImage} alt="logo" className="title-image" />
            </div>
            <div className='page-title'>
                <h1 className='title-text'>Sign up</h1>
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
                    onFinish={onFinish}
                    onFinishFailed={onFinishFailed}
                    autoComplete="off"
                >
                    <Form.Item
                    label="FirstName"
                    name="firstname"
                    rules={[
                        {
                        required: true,
                        message: 'Please input your firstname!',
                        },
                    ]}
                    >
                    <Input />
                    </Form.Item>

                    <Form.Item
                    label="LastName"
                    name="lastname"
                    rules={[
                        {
                        required: true,
                        message: 'Please input your lastname!',
                        },
                    ]}
                    >
                    <Input />
                    </Form.Item>

                    <Form.Item
                    label="Email"
                    name="email"
                    rules={[
                        {
                        required: true,
                        message: 'Please input your email!',
                        },
                    ]}
                    >
                    <Input />
                    </Form.Item>

                    <Form.Item
                    label="Password"
                    name="password"
                    rules={[
                        {
                        required: true,
                        message: 'Please input your password!',
                        },
                    ]}
                    >
                    <Input.Password />
                    </Form.Item>

                    <Form.Item
                    style={{
                        marginLeft: '48%',
                    }}>
                    <Link to="/">
                        Already have an account? Login
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
export default SignupScreen;