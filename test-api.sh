#!/bin/bash

# Shop API Test Script
# This script tests all the endpoints of the Shop API

BASE_URL="http://localhost:8080/api/shops"

echo "🚀 Testing Shop API Endpoints"
echo "================================"

echo ""
echo "1. 📋 GET All Shops:"
echo "curl -X GET \"$BASE_URL\""
curl -X GET "$BASE_URL" -H "Accept: application/json" | python3 -m json.tool 2>/dev/null || curl -X GET "$BASE_URL" -H "Accept: application/json"

echo ""
echo ""
echo "2. 🔍 GET Shop by ID (ID=1):"
echo "curl -X GET \"$BASE_URL/1\""
curl -X GET "$BASE_URL/1" -H "Accept: application/json" | python3 -m json.tool 2>/dev/null || curl -X GET "$BASE_URL/1" -H "Accept: application/json"

echo ""
echo ""
echo "3. 📊 GET Shop Count:"
echo "curl -X GET \"$BASE_URL/count\""
curl -X GET "$BASE_URL/count" -H "Accept: application/json" | python3 -m json.tool 2>/dev/null || curl -X GET "$BASE_URL/count" -H "Accept: application/json"

echo ""
echo ""
echo "4. 🔎 Search Shops (search term: 'coffee'):"
echo "curl -X GET \"$BASE_URL/search?q=coffee\""
curl -X GET "$BASE_URL/search?q=coffee" -H "Accept: application/json" | python3 -m json.tool 2>/dev/null || curl -X GET "$BASE_URL/search?q=coffee" -H "Accept: application/json"

echo ""
echo ""
echo "5. ➕ Create New Shop:"
echo "curl -X POST \"$BASE_URL\" -H \"Content-Type: application/json\" -d '{\"name\":\"API Test Shop\",\"address\":\"456 API Test Ave\",\"phone\":\"+1-555-TEST\",\"email\":\"api@testshop.com\"}'"
curl -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"name":"API Test Shop","address":"456 API Test Ave","phone":"+1-555-TEST","email":"api@testshop.com"}' | python3 -m json.tool 2>/dev/null || curl -X POST "$BASE_URL" -H "Content-Type: application/json" -H "Accept: application/json" -d '{"name":"API Test Shop","address":"456 API Test Ave","phone":"+1-555-TEST","email":"api@testshop.com"}'

echo ""
echo ""
echo "6. 📋 GET All Shops (after creation):"
echo "curl -X GET \"$BASE_URL\""
curl -X GET "$BASE_URL" -H "Accept: application/json" | python3 -m json.tool 2>/dev/null || curl -X GET "$BASE_URL" -H "Accept: application/json"

echo ""
echo ""
echo "✅ API Testing Complete!"
echo "================================"
echo "🌐 API Base URL: $BASE_URL"
echo "📖 Available Endpoints:"
echo "   GET    $BASE_URL           - Get all shops"
echo "   GET    $BASE_URL/{id}      - Get shop by ID"
echo "   POST   $BASE_URL           - Create new shop"
echo "   PUT    $BASE_URL/{id}      - Update shop"
echo "   DELETE $BASE_URL/{id}      - Delete shop"
echo "   GET    $BASE_URL/search?q={term} - Search shops"
echo "   GET    $BASE_URL/count     - Get shop count"
