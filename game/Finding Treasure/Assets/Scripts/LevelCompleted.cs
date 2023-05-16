using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class LevelCompleted : MonoBehaviour
{
    [SerializeField] private Text cherriesText;
    private readonly Dictionary<long, long> keyValuePairs = new Dictionary<long, long>();

    private void Start()
    {
        cherriesText.text = GlobalState.collectedCherries.ToString();
    }
    public LevelCompleted()
    {
        keyValuePairs.Add(2, 13);
        keyValuePairs.Add(4, 14);
        keyValuePairs.Add(6, 15);

    }
    public static bool Contains<T>(T[] array, T item)
    {
        List<T> list = new List<T>(array);
        return list.Contains(item);
    }

    public void NextLevel()
    {
        long levelId = keyValuePairs[SceneManager.GetActiveScene().buildIndex + 1];

        if (Contains(GlobalState.loginResult.user.purchasedLevels, levelId))
        {
            SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1);
            return;
        }


        bool isStarted = false;

        foreach (var item in keyValuePairs)
        {
            if (isStarted)
            {
                SceneManager.LoadScene(Convert.ToInt32(item.Key));
                return;
            }
            if(item.Value == levelId)
            {
                isStarted = true;
            }
        }

        SceneManager.LoadScene(1);
    }

    public void MainMenu()
    {
        SceneManager.LoadScene("Start Screen 1");
    }
}
