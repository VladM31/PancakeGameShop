using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class DeathAnimationController : MonoBehaviour
{// ���� ����� ����� ���������� �� Animation Event
    public void OnDeathAnimationFinished()
    {
        // �������� ���������� ����� ����� ���������� �������� ������
        SceneManager.LoadScene("DeathScene");
    }
}
