using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;


public class RestartController : MonoBehaviour
{
    public void RestartLevel1()
    {
        SceneManager.LoadScene("Level1");
    }
    public void RestartLevel2()
    {
        SceneManager.LoadScene("Level2");
    }
    public void MainMenu()
    {
        SceneManager.LoadScene("Start Screen 1");
    }
}
