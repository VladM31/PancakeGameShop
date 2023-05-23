using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TriggerPlay : MonoBehaviour
{
    public bool  isTriggered = false;
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.tag == "player" && !isTriggered)
        {
            FindObjectOfType<DialogueTrigger>().TriggerDialogue();
            isTriggered = true;
        }
    }
}
