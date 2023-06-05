using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MusicManager : MonoBehaviour
{
    public Slider sliderVolume;
    public AudioSource audio;
    public float volume;

    void Start()
    {
        Load();
        ValueMusic();
    }

    public void SliderMusic()
    {
        volume = sliderVolume.value;
        Save();
        ValueMusic();   
    }

    private void ValueMusic()
    {
        audio.volume = volume;
        sliderVolume.value = volume;
    }

    private void Save()
    {
        PlayerPrefs.SetFloat("volume", volume);

    }

    private void Load()
    {
        volume = PlayerPrefs.GetFloat("volume", volume);
    }

}


