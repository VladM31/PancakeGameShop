using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TextPosition : MonoBehaviour
{
    public Transform target; // ������ �� ������, � �������� ����� �������� �����
    private RectTransform rectTransform;
    public float yOffset = 150f; // ����������� ���� �������� ��� ��������� ��������


    private void Start()
    {
        rectTransform = GetComponent<RectTransform>();
    }

    private void Update()
    {
        if (target != null)
        {
            // �������� ������� ������� � ������� �����������
            Vector3 worldPosition = target.position;

            // ����������� ������� ������� � �������� ����������
            Vector3 screenPosition = Camera.main.WorldToScreenPoint(worldPosition);

            // ��������� �������� ����� � ������� ������
            screenPosition.y += yOffset;

            // ������������� ����� ������� ������
            rectTransform.position = screenPosition;
        }
    }
}
