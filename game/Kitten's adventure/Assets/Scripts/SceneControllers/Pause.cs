using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Pause : MonoBehaviour
{
    public bool pause = false;
    public GameObject pauseMenu;
    public GameObject settingsMenu;


    private void Start()
    {
        settingsMenu.SetActive(false);
    }

    //Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            if (pause)
            {
                Resume();
            }
            else
            {
                Payse();
            }
        }
    }


    public void Resume()
    {
        pauseMenu.SetActive(false);
        Time.timeScale = 1f;
        pause = false;
    }

    void Payse()
    {
        pauseMenu.SetActive(true);
        Time.timeScale = 0f;
        pause = true;
    }

    public void MainMenu()
    {
        Time.timeScale = 1f;
        SceneManager.LoadScene("Start Screen 1");
    }

    public void SwitchToSettings()
    {
        //pause = false;
        //pauseMenu.SetActive(false);
        settingsMenu.SetActive(true);
    }

}
