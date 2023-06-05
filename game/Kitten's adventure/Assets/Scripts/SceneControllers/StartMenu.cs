using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using UnityEngine.SceneManagement;

public class StartMenu : MonoBehaviour
{
    long[] result = GlobalState.loginResult.user.purchasedLevels;
    
    public void StartGame()
    {
        List<string> boughtLevels = new List<string>();
        for (int i = 0; i < result.Length; i++)
        {
            Debug.Log(result[i]);
            boughtLevels.Add("Level" + (result[i] - 21));
            SceneManager.LoadScene(boughtLevels[0]);
        }
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

    public void Settings()
    {
        SceneManager.LoadScene("Settings");
        Debug.Log("Settings");
    }

    public void Settings_Back()
    {
        SceneManager.LoadScene("Start Screen 1");
        Debug.Log("Settings");
    }

}
