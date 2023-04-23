using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Pause : MonoBehaviour
{
    private bool pause = false;
    private GameObject pauseMenu;


    // Update is called once per frame
    void Update()
    {
        if(Input.GetKeyDown(KeyCode.Escape))
        {
            if(pause) {
                Resume();
            } else if(pause)
            {
                MainMenu();
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
        Time.timeScale = 1;
        pause = false;
    }

    void Payse()
    {
        pauseMenu.SetActive(true);
        Time.timeScale = 0;
        pause = true;
    }

    public void MainMenu()
    {
        Time.timeScale = 1;
        SceneManager.LoadScene("Start Screen 1");
    }

}
