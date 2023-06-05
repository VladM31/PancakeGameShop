using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour
{
    private Rigidbody2D rb;
    private BoxCollider2D coll;
    private SpriteRenderer sprite;
    private Animator anim;

    [SerializeField] private float moveSpeed = 7f;
    [SerializeField] private float jumpForce = 12f;
    [SerializeField] private float waterSpeedMultiplier = 0.33f;
    [SerializeField] private LayerMask jumpableGround;
    [SerializeField] private LayerMask waterLayer;
    [SerializeField] private AudioSource walkSoundEffect;

    private float dirX = 0f;
    private bool isInWater = false;

    private enum MovementState { idle, running, jumping, falling }

    private void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        coll = GetComponent<BoxCollider2D>();
        sprite = GetComponent<SpriteRenderer>();
        anim = GetComponent<Animator>();
    }

    private void Update()
    {
        dirX = Input.GetAxisRaw("Horizontal");

        if (isInWater)
        {
            rb.velocity = new Vector2(dirX * moveSpeed * waterSpeedMultiplier, rb.velocity.y);
        }
        else
        {
            rb.velocity = new Vector2(dirX * moveSpeed, rb.velocity.y);
        }

        if (Input.GetButtonDown("Jump") && IsGrounded())
        {
            rb.velocity = new Vector2(rb.velocity.x, jumpForce);
        }

        UpdateAnimationState();
    }

    private void UpdateAnimationState()
    {
        MovementState state;

        if (dirX > 0f)
        {
            state = MovementState.running;
            sprite.flipX = false;
        }
        else if (dirX < 0f)
        {
            state = MovementState.running;
            sprite.flipX = true;
        }
        else
        {
            state = MovementState.idle;
            walkSoundEffect.Play();
        }

        if (rb.velocity.y > 0.1f)
        {
            state = MovementState.jumping;
        }
        else if (rb.velocity.y < -0.1f)
        {
            state = MovementState.falling;
        }

        anim.SetInteger("state", (int)state);
    }

    private bool IsGrounded()
    {
        return Physics2D.BoxCast(coll.bounds.center, coll.bounds.size, 0f, Vector2.down, 0.1f, jumpableGround);
    }

    private void CheckWater()
    {
        isInWater = Physics2D.OverlapArea(new Vector2(coll.bounds.min.x, coll.bounds.min.y),
                                          new Vector2(coll.bounds.max.x, coll.bounds.min.y - 0.01f),
                                          waterLayer);
    }

    private void FixedUpdate()
    {
        CheckWater();
    }
}