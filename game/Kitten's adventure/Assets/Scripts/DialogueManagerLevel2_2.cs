using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DialogueManagerLevel2_2 : MonoBehaviour
{
    public Text dialogueText;
    public GameObject enterText;

    public Rigidbody2D rbPlayer;
    public GameObject rbNPC;

    public CofferMovement cofferMovement;

    private Queue<string> sentences;

    public GameObject key;
    public Transform keyAppearingPoint;

    void Start()
    {
        sentences = new Queue<string>();
        enterText.SetActive(false);
        key.SetActive(false);
        key.transform.position = keyAppearingPoint.position;
    }

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.Return) && cofferMovement.isTouched)
        {
            DisplayNextSentence();
        }
    }
    public void StartDialogue(Dialogue dialogue)
    {
        rbPlayer.bodyType = RigidbodyType2D.Static;
        sentences.Clear();

        foreach (string sentence in dialogue.sentences)
        {
            sentences.Enqueue(sentence);
        }
        enterText.SetActive(true);

        DisplayNextSentence();
    }

    private void DisplayNextSentence()
    {
        if (sentences.Count == 0)
        {
            EndDialogue();
            return;
        }
        else if (sentences.Count == 3)
        {
            key.SetActive(true);
        }
        string sentence = sentences.Dequeue();
        Debug.Log(sentence);
        dialogueText.text = sentence;
        StopAllCoroutines();
        StartCoroutine(TypeSentence(sentence));
    }

    IEnumerator TypeSentence(string sentence)
    {
        dialogueText.text = "";
        foreach (char letter in sentence.ToCharArray())
        {
            dialogueText.text += letter;
            yield return new WaitForSeconds(0.05f);
        }
    }

    private void EndDialogue()
    {
        dialogueText.text = "";
        rbPlayer.bodyType = RigidbodyType2D.Dynamic;
        enterText.SetActive(false);
        Debug.Log("END");
        key.SetActive(false);
    }

}


