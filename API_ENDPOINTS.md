# Temple Database API Endpoints

## Authentication Endpoints

### Register
- **POST** `/api/auth/register`
- **Body:**
```json
{
  "name": "John Doe",
  "phone": "1234567890",
  "password": "password123"
}
```

### Login
- **POST** `/api/auth/login`
- **Body:**
```json
{
  "phone": "1234567890",
  "password": "password123"
}
```

## Members Endpoints

- **GET** `/api/members` - Get all members
- **GET** `/api/members/{id}` - Get member by ID
- **POST** `/api/members` - Create member
- **PUT** `/api/members/{id}` - Update member
- **DELETE** `/api/members/{id}` - Delete member

**Create/Update Member Body:**
```json
{
  "memberName": "John Doe",
  "memberPhoneNo": "1234567890",
  "memberDetails": "Member details",
  "date": "2024-01-15",
  "poojaId": 1
}
```
*Note: Price is automatically set from the selected Pooja*

## Staff Endpoints

- **GET** `/api/staff` - Get all staff
- **GET** `/api/staff/{id}` - Get staff by ID
- **POST** `/api/staff` - Create staff
- **PUT** `/api/staff/{id}` - Update staff
- **DELETE** `/api/staff/{id}` - Delete staff

**Create/Update Staff Body:**
```json
{
  "staffName": "Jane Smith",
  "staffPhone": "1234567890",
  "staffDetails": "Staff details",
  "staffRole": "Manager",
  "staffSalary": 50000.00
}
```

## Hall Endpoints

- **GET** `/api/halls` - Get all halls
- **GET** `/api/halls/{id}` - Get hall by ID
- **POST** `/api/halls` - Create hall
- **PUT** `/api/halls/{id}` - Update hall
- **DELETE** `/api/halls/{id}` - Delete hall

**Create/Update Hall Body:**
```json
{
  "hallName": "Main Hall",
  "hallDetails": "Hall details"
}
```

## Hall Booking Endpoints

- **GET** `/api/hall-bookings` - Get all bookings
- **GET** `/api/hall-bookings/{id}` - Get booking by ID
- **POST** `/api/hall-bookings` - Create booking
- **PUT** `/api/hall-bookings/{id}` - Update booking
- **DELETE** `/api/hall-bookings/{id}` - Delete booking

**Create/Update Booking Body:**
```json
{
  "name": "Event Organizer",
  "phoneNo": "1234567890",
  "details": "Event details",
  "price": 5000.00,
  "date": "2024-02-15",
  "hallId": 1
}
```

## Pooja Endpoints

- **GET** `/api/poojas` - Get all poojas
- **GET** `/api/poojas/{id}` - Get pooja by ID
- **POST** `/api/poojas` - Create pooja
- **PUT** `/api/poojas/{id}` - Update pooja
- **DELETE** `/api/poojas/{id}` - Delete pooja

**Create/Update Pooja Body:**
```json
{
  "poojaName": "Ganapati Pooja",
  "poojaDetails": "Pooja details",
  "poojaPrice": 1000.00
}
```

## Volunteers Endpoints

- **GET** `/api/volunteers` - Get all volunteers
- **GET** `/api/volunteers/{id}` - Get volunteer by ID
- **POST** `/api/volunteers` - Create volunteer
- **PUT** `/api/volunteers/{id}` - Update volunteer
- **DELETE** `/api/volunteers/{id}` - Delete volunteer

**Create/Update Volunteer Body:**
```json
{
  "volunteerName": "Volunteer Name",
  "volunteerPhone": "1234567890",
  "volunteerDetail": "Volunteer details",
  "date": "2024-01-15"
}
```

## Database Configuration

Update `application.properties` with your MySQL credentials:
- Database: `temple_db`
- Username: `root`
- Password: `root` (change as needed)
- Port: `3306`

## Notes

- All dates should be in `YYYY-MM-DD` format
- Phone numbers must be exactly 10 digits
- Prices are stored as BigDecimal for precision
- Member price is automatically set from the selected Pooja
- Hall bookings require an existing Hall ID (use GET /api/halls to get available halls)
- Member creation requires an existing Pooja ID (use GET /api/poojas to get available poojas)





