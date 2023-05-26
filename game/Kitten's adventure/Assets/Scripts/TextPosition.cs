using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TextPosition : MonoBehaviour
{
    public Transform target; // ������ �� ������, � �������� ����� �������� �����

    private RectTransform rectTransform;

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
            float yOffset = 150f; // ����������� ���� �������� ��� ��������� ��������
            screenPosition.y += yOffset;

            // ������������� ����� ������� ������
            rectTransform.position = screenPosition;
        }
    }
}
