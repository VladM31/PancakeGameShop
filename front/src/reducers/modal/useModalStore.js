import {useSelector} from "react-redux";

export const useModalStore = () => useSelector(state => state.modalStore);
