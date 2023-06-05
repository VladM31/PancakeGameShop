using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MusicManagerInGame : MonoBehaviour
{
    public GameObject settingsMenu;

    public Slider sliderVolume;
    public float volume;

    public MusicInGame allSounds;

    public Pause pauseMenu;

    public void Start()
    {
        Load();
        allSounds.ValueMusic();
        sliderVolume.value = allSounds.volume;
    }

    public void SliderMusic()
    {
        allSounds.volume = sliderVolume.value;
        Save();
        allSounds.ValueMusic();
    }

    private void Save()
    {
        PlayerPrefs.SetFloat("volume", allSounds.volume);

    }

    private void Load()
    {
        volume = PlayerPrefs.GetFloat("volume", allSounds.volume);
    }

    public void Back()
    {
        settingsMenu.SetActive(false);
        pauseMenu.GetComponent<Pause>().Resume();
        //Time.timeScale = 1f;
    }
}
