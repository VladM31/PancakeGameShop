using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerLife : MonoBehaviour
{
    public Transform otherObject; // ������ �� ������ ������, � ������� ������������ ���������
    public float maxDistance = 5f; 

    void Update()
    {
        // �������� ��������� ����� ���������� � ������ ��������
        float distance = Vector2.Distance(transform.position, otherObject.position);

        // ���� ��������� ��������� ������������ ���������, �������� �������
        if (distance > maxDistance)
        {
            Die();
        }
    }

    void Die()
    {
        Debug.Log("You dead");  
    }
}

