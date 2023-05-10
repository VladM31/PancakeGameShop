import React, {useEffect, useState} from 'react';
import { checkUser } from "../../../reducers/user/userStore";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

const RegisterHelp = ({ telephone }) => {
    const [status, setStatus] = useState(false);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        let intervalId = setInterval(async () => {
            if (status) {
                clearInterval(intervalId);
                navigate('/');
            } else {
                const res = await dispatch(checkUser(telephone));
                setStatus(res.payload);
            }
        }, 2000);

        return () => {
            clearInterval(intervalId);
        };
    }, [dispatch, navigate, status, telephone]);

    const arr = [
        { text: 'Перейдіть до телеграм бота.' },
        { text: 'Натисніть кнопку start.' },
        { text: 'Натисніть кнопку Contact.' },
        { text: 'Зачикайте 10-30 секунд.' },
        { text: 'Під повідомленням бота натисніть кнопку підтвердити реєстрацію.' },
        { text: 'Вернітся на сайт та увійдіть в систему.' },
    ];

    return (
        <div style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', width: 700, height: 500, background: 'rgba(181, 93, 156, 0.85)', borderRadius: 30 }}>
            <h2 style={{ fontWeight: 400, fontSize: 36, margin: '30px 0 0 0', textAlign: 'center', color: '#FFFFFF' }}>Інструкція з подальшої реєстрації</h2>
            <ul>
                {
                    arr.map((item, index) => {
                        return (
                            <li style={{ fontSize: 22, color: '#ffffff', listStyleType: 'none' }} key={index}>
                                <p>{index + 1}. {item.text} {index === 0 ? <a target='_blank' style={{fontStyle: "italic", fontSize: '16px', textDecoration: 'none', color: 'white'}} href={'https://t.me/needlework_number_bot'}>(телеграм бот)</a> : null}</p>
                            </li>
                        )
                    })
                }
            </ul>
        </div>
    );
};

export default RegisterHelp;