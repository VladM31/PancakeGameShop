using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LevelCompleted : MonoBehaviour
{
    long[] result = GlobalState.loginResult.user.purchasedLevels;
    public void NextLevel()
    {
        List<string> boughtLevels = new List<string>();
        for (int i = 0; i < result.Length; i++)
        {
            boughtLevels.Add("Level" + (result[i] - 12));
            SceneManager.LoadScene(boughtLevels[i]);
        }
       
    }



    public void MainMenu()
    {
        SceneManager.LoadScene("Start Screen 1");
    }
}
