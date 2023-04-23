import React from 'react';

const RegisterHelp = () => {
    const arr = [
        {text: 'Перейдіть за послання до теграм бота.'},
        {text: 'Натисніть кнопку start.'},
        {text: 'Натисніть кнопку Contact.'},
        {text: 'Зачикайте 10-30 секунд.'},
        {text: 'Під повідомленням бота натисніть кнопку підтвердити реєстрацію.'},
        {text: 'Вернітся на сайт та увійдіть в систему.'},
    ];

    return (
        <div style={{display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', width: 700, height: 500, background: 'rgba(181, 93, 156, 0.85)', borderRadius: 30}}>
            <h2 style={{fontWeight: 400, fontSize: 36, margin: '30px 0 0 0', textAlign: 'center', color: '#FFFFFF'}}>Інструкція з подальшої реєстрації</h2>
            <ul>
                {
                    arr.map((item, index) => {
                        return (
                            <li style={{fontSize: 22, color: '#ffffff', listStyleType: 'none'}} key={index}>
                                <p>{index + 1}. {item.text}</p>
                            </li>
                        )
                    })
                }
            </ul>
        </div>
    );
};

export default RegisterHelp;