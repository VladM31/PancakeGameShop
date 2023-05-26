import React from 'react';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import {useDispatch} from "react-redux";
import {useModalStore} from "../../../reducers/modal/useModalStore";
import {closeVideoModal} from "../../../reducers/modal/modalStore";
import ReactPlayer from "react-player";

function VideoModal() {
    const dispatch = useDispatch();
    const {videoModalUrl, isVideoModal} = useModalStore()

    return (
        <div>
            <Dialog
                open={isVideoModal}
                onClose={() => dispatch(closeVideoModal())}
                aria-labelledby="form-dialog-title"
                fullWidth={true}
                maxWidth="sm"
            >
                <DialogContent sx={{
                    width: '31.25vw',
                    height: '40vh',
                    backgroundColor: 'black'
                }}>
                    <ReactPlayer
                        url={videoModalUrl}
                        width="100%"
                        height="100%"
                        controls
                    />
                </DialogContent>
            </Dialog>
        </div>
    );
}

export default VideoModal;