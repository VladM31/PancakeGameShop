using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class DeathAnimationController : MonoBehaviour
{// Этот метод будет вызываться из Animation Event
    public void OnDeathAnimationFinished()
    {
        // Загрузка конкретной сцены после завершения анимации смерти
        SceneManager.LoadScene("DeathScene");
    }
}
