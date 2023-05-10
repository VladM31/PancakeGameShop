using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using UnityEngine.Networking;
using System;
//using UnityEngine.UIElements;

public class Levels : MonoBehaviour
{
    
    public Button level1B;
    public Button level2B;
    public Button level3B;
   
    //int levelComplete;
   
    //void Start()
    //{

    //    levelComplete = PlayerPrefs.GetInt("LevelComplete");
    //    level2B.interactable = false;
    //    level3B.interactable = false;

    //    switch (levelComplete)
    //    {
    //        case 1:
    //            level2B.interactable = true;
    //            break;
    //        case 2:
    //            level2B.interactable = true;
    //            level3B.interactable = true;
    //            break;
    //    }
    //}
    public void LoadTo(int level)
    {
        SceneManager.LoadScene(level);

    }

    public void BackToMenu()
    {
        SceneManager.LoadScene("Start Screen 1");
    }

    public void Buy()
    {
        Application.OpenURL("http://localhost:3000/game/1");
    }

    public GameObject[] levels;
    long[] result = GlobalState.loginResult.user.purchasedLevels;
    Dictionary<string,Button> keyValuePairs = new Dictionary<string,Button>();

    void Start()
    {
        keyValuePairs.Add("Level1", level1B);
        keyValuePairs.Add("Level2", level2B);
        keyValuePairs.Add("Level3", level3B);
        // Сховати всі рівні
        HideAllLevels();

        // Знайти і відобразити активний рівень
        ShowLevelByID();
    }

    // Метод для сховання всіх рівнів
    void HideAllLevels()
    {
        foreach (GameObject level in levels)
        {
            level.SetActive(false);
        }
    }

    // Метод для відображення рівня за його ID
    void ShowLevelByID()
    {
        List<string> boughtLevels = new List<string>();
        for (int i = 0; i < result.Length; i++)
        {
            boughtLevels.Add("Level" + (result[i] - 12));

        }
        foreach (GameObject level in levels)
        {
           Debug.Log(level.name);

            if (boughtLevels.Contains(level.name))
            {
                level.SetActive(true);
                keyValuePairs[level.name].interactable = true;
            }
            else
            {
                level.SetActive(false);
            }
        }
        
        
    }


}


