using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerInteraction : MonoBehaviour
{
    private bool isNearCoffer = false;
    public bool isPicked = false;
    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.E) && isNearCoffer)
        {
            Debug.Log("Hello");
            isPicked = true;
        }
    }
    public void OnTriggerStay2D(Collider2D collision)
    {
        if( collision.gameObject.CompareTag("Coffer"))
        {
            isNearCoffer = true;

        }
    }

    public void OnTriggerExit2D(Collider2D collision)
    {
        if (collision.gameObject.CompareTag("Coffer"))
        {
            isNearCoffer = false;
        }
    }
}


