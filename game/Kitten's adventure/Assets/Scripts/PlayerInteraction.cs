using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerInteraction : MonoBehaviour
{
    private bool isNearCoffer = false;
    public bool isPicked = false;
    public GameObject text;

    private bool isAlreadyPicked = false;


    public Dialogue dialogue;

    public void TriggerDialogue()
    {
        FindObjectOfType<DialogueManagerLeve2>().StartDialogue(dialogue);
    }

    private void Start()
    {
        text.SetActive(false);
    }
    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.E) && isNearCoffer &&!isAlreadyPicked)
        {
            Debug.Log("Hello");
            isPicked = true;
            isAlreadyPicked = true;
            text.SetActive(false);
            TriggerDialogue();
        }
    }

    public void OnTriggerStay2D(Collider2D collision)
    {
        if( collision.gameObject.CompareTag("Coffer") && !isAlreadyPicked)
        {
            isNearCoffer = true;
            text.SetActive(true);

        }
    }

    public void OnTriggerExit2D(Collider2D collision)
    {
        if (collision.gameObject.CompareTag("Coffer") && !isAlreadyPicked)
        {
            isNearCoffer = false;
            text.SetActive(false);

        }
    }
}


