using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MusicInGame : MonoBehaviour
{
    public float volume;

    void Start()
    {  
        Load();
        ValueMusic();
    }

    public void ValueMusic()
    {
        AudioSource[] audioSources = FindObjectsOfType<AudioSource>();

        foreach (var audioSource in audioSources)
        {
            audioSource.volume = volume;
        }
    }

    public void Load()
    {
        volume = PlayerPrefs.GetFloat("volume", volume);
    }
}
