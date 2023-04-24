import React, {useState} from 'react';
import RegistrationForm from "../components/UI/Forms/RegisterForm";
import RegisterHelp from "../components/UI/Etc/RegisterHelp";

function SignUp() {
    const [isActive, setIsActive] = useState(false);
    return (
        <>
            {
                isActive ? <RegisterHelp/> : <RegistrationForm isActiveFunc={setIsActive}/>
            }
        </>
    );
}

export default SignUp;