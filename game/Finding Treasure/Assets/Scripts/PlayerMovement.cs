using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour
{
    private Rigidbody2D rb;
    private Animator anim;
    private SpriteRenderer sprite;

    [SerializeField] private float moveSpeed = 7f;
    [SerializeField] private float jumpForce = 14f;

    private float dirX = 0f;

    //private enum MovementState {idle, running, jumping, falling }
    private enum MovementState {idle, running, jumping, falling }

    // Start is called before the first frame update
    private void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        sprite = GetComponent<SpriteRenderer>();
        anim = GetComponent<Animator>();
    }

    // Update is called once per frame
    private void Update()
    {
        dirX = Input.GetAxisRaw("Horizontal");

        rb.velocity = new Vector2(dirX * moveSpeed, rb.velocity.y);


        if (Input.GetButtonDown("Jump"))
        {
            rb.velocity = new Vector2(rb.velocity.x, jumpForce);
        }

        UpdateAnimationState();
      
    }

    private void UpdateAnimationState()
    {
      //MovementState state ;
      MovementState state ;

        if (dirX > 0f)
        {
<<<<<<< HEAD
           //state = MovementState.running;
=======
            state = MovementState.running;
>>>>>>> dev
            sprite.flipX = false;
        }
        else if (dirX < 0f)
        {
<<<<<<< HEAD
           //state = MovementState.running;
=======
            state = MovementState.running;
>>>>>>> dev
            sprite.flipX= true;
        }
        else
        {
            //state = MovementState.idle;
            state = MovementState.idle;
        }


        if (rb.velocity.y > .1f)
        {
<<<<<<< HEAD
           //state = MovementState.jumping;
=======
            state = MovementState.jumping;
>>>>>>> dev

        }
        else if (rb.velocity.y < -.1f)
        {
<<<<<<< HEAD
           //state = MovementState.falling;
        }

       //anim.SetInteger("state", (int)state );
=======
            state = MovementState.falling;
        }

        anim.SetInteger("state", (int)state );
>>>>>>> dev
    }
}
