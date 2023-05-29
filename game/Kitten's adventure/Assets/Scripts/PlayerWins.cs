using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerWins : MonoBehaviour
{
    public Dialogue dialogue;
    public CofferMovement coffer;

    public GameObject player;
    public GameObject cofferNPC;


    public Transform [] targetPoints;  // Целевая точка, куда нужно переместить игрока
    
    public void TriggerDialogue()
    {
        FindObjectOfType<DialogueManagerLevel2_2>().StartDialogue(dialogue);
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (coffer.isMoving && collision.gameObject.tag.Equals("Coffer") && !coffer.isTouched && coffer.firstPointIsGone)
        {
            coffer.isTouched = true;
            coffer.isMoving = false;
            Teleport();
            Invoke("TriggerDialogue", 1f);
        }
    }

    private void Teleport()
    {
        player.transform.position = targetPoints[0].position;
        cofferNPC.transform.position = targetPoints[1].position;

    }
}
