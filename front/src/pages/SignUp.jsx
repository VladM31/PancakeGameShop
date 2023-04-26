import React, {useState} from 'react';
import RegistrationForm from "../components/UI/Forms/RegisterForm";
import RegisterHelp from "../components/UI/Etc/RegisterHelp";

function SignUp() {
    const [isActive, setIsActive] = useState(false);
    const [telephone, setIsTelephone] = useState('');
    return (
        <>
            {
                isActive ? <RegisterHelp telephone={telephone}/> : <RegistrationForm setIsActive={setIsActive} setIsTelephone={setIsTelephone}/>
            }
        </>
    );
}

export default SignUp;