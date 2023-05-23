using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerLife : MonoBehaviour
{
    public Transform otherObject; // Ссылка на другой объект, с которым сравнивается дистанция
    public float maxDistance = 5f; 

    void Update()
    {
        // Проверка дистанции между персонажем и другим объектом
        float distance = Vector2.Distance(transform.position, otherObject.position);

        // Если дистанция превышает максимальную дистанцию, персонаж умирает
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

