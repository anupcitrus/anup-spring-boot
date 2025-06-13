#!/bin/bash

# Swagger Documentation Test Script
# This script tests the Swagger/OpenAPI documentation endpoints

BASE_URL="http://localhost:8080"

echo "📚 Testing Swagger/OpenAPI Documentation"
echo "========================================"

echo ""
echo "1. 📋 OpenAPI JSON Specification:"
echo "curl -X GET \"$BASE_URL/api-docs\""
echo "Response (first 500 characters):"
curl -X GET "$BASE_URL/api-docs" -H "Accept: application/json" 2>/dev/null | head -c 500
echo "..."

echo ""
echo ""
echo "2. 🌐 Swagger UI Redirect Test:"
echo "curl -I \"$BASE_URL/swagger-ui.html\""
curl -I "$BASE_URL/swagger-ui.html" 2>/dev/null

echo ""
echo "3. 🎨 Swagger UI Main Page:"
echo "curl -I \"$BASE_URL/swagger-ui/index.html\""
curl -I "$BASE_URL/swagger-ui/index.html" 2>/dev/null

echo ""
echo "4. 📊 API Documentation Summary:"
echo "Extracting API information from OpenAPI spec..."

# Extract key information from OpenAPI spec
API_INFO=$(curl -s "$BASE_URL/api-docs" | python3 -c "
import json, sys
try:
    data = json.load(sys.stdin)
    print(f\"Title: {data['info']['title']}\")
    print(f\"Version: {data['info']['version']}\")
    print(f\"Description: {data['info']['description'][:100]}...\")
    print(f\"Total Endpoints: {len(data['paths'])}\")
    print(f\"Available Operations:\")
    for path, methods in data['paths'].items():
        for method, details in methods.items():
            if 'summary' in details:
                print(f\"  {method.upper()} {path} - {details['summary']}\")
except:
    print('Could not parse API documentation')
" 2>/dev/null)

echo "$API_INFO"

echo ""
echo ""
echo "✅ Swagger Documentation Testing Complete!"
echo "=========================================="
echo ""
echo "🌐 Access Points:"
echo "   📖 OpenAPI Spec (JSON): $BASE_URL/api-docs"
echo "   🎨 Swagger UI:          $BASE_URL/swagger-ui.html"
echo "   🔗 Direct UI:           $BASE_URL/swagger-ui/index.html"
echo ""
echo "🚀 Features Available in Swagger UI:"
echo "   • Interactive API testing"
echo "   • Request/Response examples"
echo "   • Schema documentation"
echo "   • Try-it-out functionality"
echo "   • Download OpenAPI spec"
echo ""
echo "💡 Usage Tips:"
echo "   • Open $BASE_URL/swagger-ui.html in your browser"
echo "   • Use 'Try it out' buttons to test endpoints"
echo "   • View detailed request/response schemas"
echo "   • Copy curl commands for external testing"
