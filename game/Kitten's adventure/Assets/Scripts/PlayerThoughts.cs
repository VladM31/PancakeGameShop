using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerThoughts : MonoBehaviour
{
    [SerializeField] GameObject thought;
    [SerializeField] AudioSource playerThoughts;

    void Start()
    {
        thought.SetActive(false);
        Invoke("Display", 2f);
    }

    void Display()
    {
        thought.SetActive(true);
        playerThoughts.Play();
        Invoke("Hide", 6f);

    }

    void Hide()
    {
        thought.SetActive(false);
    }
}
