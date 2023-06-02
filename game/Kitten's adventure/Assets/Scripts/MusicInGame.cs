using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MusicInGame : MonoBehaviour
{
    public AudioSource[] audio;
    private float volume;

    void Start()
    {  
        Load();
        ValueMusic();
    }

    private void ValueMusic()
    {
        foreach (var item in audio)
        {
            item.volume = volume;
        }
    }

    private void Load()
    {
        volume = PlayerPrefs.GetFloat("volume", volume);
    }
}
