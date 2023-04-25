using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class StartMenu : MonoBehaviour
{
    public void StartGame()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1);
    }

    public void SelectLevel()
    {
        SceneManager.LoadScene("Levels"); 
    }

    public void Quit()
    {
        Application.Quit();
        Debug.Log("Exit");
    }
}
