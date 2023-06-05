using System.Collections;
using System.Collections.Generic;
using UnityEditor.Rendering;
using UnityEngine;
using UnityEngine.UI;

public class DialogueManagerLeve2 : MonoBehaviour
{
    public Text dialogueText;
    public GameObject enterText;

    public Rigidbody2D rbPlayer;
    public GameObject rbNPC;

    public CofferMovement cofferMovement;

    private Queue<string> sentences;

    public AudioSource cofferVoice;
    void Start()
    {
        sentences = new Queue<string>();
        enterText.SetActive(false);
    }

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.Return))
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

    public void DisplayNextSentence()
    {
        if (sentences.Count == 0)
        {
            EndDialogue();
            return;
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
            cofferVoice.Play();
            yield return new WaitForSeconds(0.05f);
        }
    }

    void EndDialogue()
    {
        dialogueText.text = "";
        enterText.SetActive(false);
        Debug.Log("END");
        cofferMovement.isMoving = true;
    }

}