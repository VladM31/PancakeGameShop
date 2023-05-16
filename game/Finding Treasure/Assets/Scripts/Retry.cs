using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Retry : MonoBehaviour
{
    [SerializeField] private Text cherriesText;

    private void Start()
    {
        cherriesText.text = GlobalState.collectedCherries.ToString();
    }


    public void RetryLevel()
    {
        SceneManager.LoadScene("Levels");
    }

    public void MainMenu()
    {
        SceneManager.LoadScene("Start Screen 1");
    }
}
