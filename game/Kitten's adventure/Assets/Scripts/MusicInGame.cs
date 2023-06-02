using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MusicInGame : MonoBehaviour
{
    private float volume;

    void Start()
    {  
        Load();
        ValueMusic();
    }

    private void ValueMusic()
    {
        AudioSource[] audioSources = FindObjectsOfType<AudioSource>();

        foreach (var audioSource in audioSources)
        {
            audioSource.volume = volume;
        }
    }

    private void Load()
    {
        volume = PlayerPrefs.GetFloat("volume", volume);
    }
}
