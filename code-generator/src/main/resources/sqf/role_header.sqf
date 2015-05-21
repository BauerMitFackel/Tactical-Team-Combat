TTC_SHOP_ARTICLE_id				= 0;
TTC_SHOP_ARTICLE_name			= 1;
TTC_SHOP_ARTICLE_price			= 2;
TTC_SHOP_ARTICLE_displayName	= 3;
TTC_SHOP_ARTICLE_description	= 4;
TTC_SHOP_ARTICLE_picture		= 5;
TTC_SHOP_ARTICLE_maxAmount		= 6;

TTC_SHOP_CATEGORY_id			= 0;
TTC_SHOP_CATEGORY_name			= 1;
TTC_SHOP_CATEGORY_icon			= 2;
TTC_SHOP_CATEGORY_articles		= 3;


_getArticle = {

	private["_id","_name","_maxAmount","_price","_config","_descriptionShort","_descriptionLong","_descriptionUse","_displayName","_picture","_description","_data"];

	_id					= [_this, 0] call BIS_fnc_param;
	_name				= [_this, 1] call BIS_fnc_param;
	_maxAmount			= [_this, 2] call BIS_fnc_param;
	_price				= [_this, 3] call BIS_fnc_param;
	_config				= [_this, 4] call BIS_fnc_param;
	_descriptionShort	= [_this, 5, (getText(configFile >> _config >> _name >> "descriptionShort")), [""]] call BIS_fnc_param;
	_descriptionLong	= [_this, 6, "", [""]] call BIS_fnc_param;
	_descriptionUse		= [_this, 7, (getText(configFile >> _config >> _name >> "descriptionUse")), [""]] call BIS_fnc_param;
	_displayName		= [_this, 8, (getText(configFile >> _config >> _name >> "displayName")), [""]] call BIS_fnc_param;
	_picture			= [_this, 9, (getText(configFile >> _config >> _name >> "picture")), [""]] call BIS_fnc_param;

	_description = "";

	if (_descriptionShort != "") then {
		_description = _descriptionShort;
	};

	if (_descriptionUse != "") then {
		if (_description != "") then {
			_description = _description + format ["<br/>%1", _descriptionUse];
		} else {
			_description = _descriptionUse;
		};
	};

	if (_descriptionLong != "") then {
		if (_description != "") then {
			_description = _description + format ["<br/><br/>%1", _descriptionLong];
		} else {
			_description = _descriptionLong;
		};
	};

	_data = [];
	_data set [TTC_SHOP_ARTICLE_id, _id];
	_data set [TTC_SHOP_ARTICLE_name, _name];
	_data set [TTC_SHOP_ARTICLE_maxAmount, _maxAmount];
	_data set [TTC_SHOP_ARTICLE_price, _price];
	_data set [TTC_SHOP_ARTICLE_displayName, _displayName];
	_data set [TTC_SHOP_ARTICLE_description, _description];
	_data set [TTC_SHOP_ARTICLE_picture, _picture];
	_data;
};

_getCapacity = {

	private ["_name","_container","_capacity"];

	_name	    = [_this, 0] call BIS_fnc_param;
	_container	= getText	(configFile >> "CfgWeapons" >> _name >> "ItemInfo" >> "containerClass");
	_capacity	= getNumber	(configFile >> "CfgVehicles" >> _container >> "maximumLoad");

	_capacity;
};

_getWeapon = {

	private ["_name","_text"];

	_name   = [_this, 1] call BIS_fnc_param;
	_short	= getText	(configFile >> "CfgWeapons" >> _name >> "descriptionShort");
	_text   = getText	(configFile >> "CfgWeapons" >> _name >> "Library" >> "libTextDesc");

	(_this + ["CfgWeapons", _short, _text]) call _getArticle;
};

_getMagazine = {
	(_this + ["CfgMagazines"]) call _getArticle;
};

_getItem = {
	(_this + ["CfgWeapons"]) call _getArticle;
};

_getGlasses = {
	(_this + ["CfgGlasses"]) call _getArticle;
};

_countVehicleSeats = {

	private ["_name","_hasDriver","_transport","_seats","_turrets","_class","_hasGunner"];

	_name	    = [_this, 0] call BIS_fnc_param;
	_hasDriver	= getNumber	(configFile >> "CfgVehicles" >> _name >> "hasDriver");
	_transport	= getNumber	(configFile >> "CfgVehicles" >> _name >> "transportSoldier");
	_seats		= _hasDriver + _transport;
	_turrets	= (configFile >> "CfgVehicles" >> _name >> "Turrets");

	for "_i" from 0 to count _turrets -1 do {
		 _class = _turrets select _i;

		if (isClass _class) then {
			//_isPersonTurret = getNumber	(configFile >> "CfgVehicles" >> _name >> "Turrets" >> configName _class >> "isPersonTurret");
			_hasGunner = getNumber	(configFile >> "CfgVehicles" >> _name >> "Turrets" >> configName _class >> "hasGunner");
			_seats = _seats + _hasGunner;
		};
	};

	_seats;
};

_getUniform = {

	private ["_name","_mass","_capacity","_description"];

	_name	        = [_this, 1] call BIS_fnc_param;
	_mass		    = getNumber	(configFile >> "CfgWeapons" >> _name >> "ItemInfo" >> "mass");
	_capacity	    = [_name] call _getCapacity;
	_description	= format["Mass: %1<br/>Capacity: %2", _mass, _capacity];

	(_this + ["CfgWeapons", _description]) call _getArticle;
};

_getVest = {

	private ["_name","_mass","_capacity","_armor","_description"];

	_name	        = [_this, 1] call BIS_fnc_param;
	_mass		    = getNumber	(configFile >> "CfgWeapons" >> _name >> "ItemInfo" >> "mass");
	_capacity	    = [_name] call _getCapacity;
	_armor		    = getNumber	(configFile >> "CfgWeapons" >> _name >> "ItemInfo" >> "armor");
	_description	= format["Mass: %1<br/>Capacity: %2<br/>Armor: %3", _mass, _capacity, _armor];

	(_this + ["CfgWeapons", _description]) call _getArticle;
};

_getBackpack = {

	private ["_name","_mass","_capacity","_description"];

	_name	        = [_this, 1] call BIS_fnc_param;
	_mass		    = getNumber	(configFile >> "CfgVehicles" >> _name >> "mass");
	_capacity	    = getNumber	(configFile >> "CfgVehicles" >> _name >> "maximumLoad");
	_description	= format["Mass: %1<br/>Capacity: %2", _mass, _capacity];

	(_this + ["CfgVehicles", _description]) call _getArticle;
};

_getVehicle = {

	private ["_name","_capacity","_armor","_seats","_maxSpeed","_text","_description"];

	_name	        = [_this, 1] call BIS_fnc_param;
	_capacity	    = getNumber	(configFile >> "CfgVehicles" >> _name >> "maximumLoad");
	_armor		    = getNumber	(configFile >> "CfgVehicles" >> _name >> "armor");
	_seats		    = [_name] call _countVehicleSeats;
	_maxSpeed	    = getNumber	(configFile >> "CfgVehicles" >> _name >> "maxSpeed");
	_text		    = getText	(configFile >> "CfgVehicles" >> _name >> "Library" >> "libTextDesc");
	_description	= format["Capacity: %1<br/>Armor: %2<br/>Seats: %3<br/>Max Speed: %4<br/><br/>%5", _capacity, _armor, _seats, _maxSpeed, _text];

	(_this + ["CfgVehicles", _description]) call _getArticle;
};


_getUGV = {

	private ["_name","_capacity","_armor","_hasDriver","_maxSpeed","_text","_description"];

	_name	        = [_this, 1] call BIS_fnc_param;
	_capacity	    = getNumber	(configFile >> "CfgVehicles" >> _name >> "maximumLoad");
	_armor		    = getNumber	(configFile >> "CfgVehicles" >> _name >> "armor");
	_hasDriver	    = getNumber	(configFile >> "CfgVehicles" >> _name >> "hasDriver");
	_maxSpeed	    = getNumber	(configFile >> "CfgVehicles" >> _name >> "maxSpeed");
	_text		    = getText	(configFile >> "CfgVehicles" >> _name >> "Library" >> "libTextDesc");
	_description	= format["Capacity: %1<br/>Armor: %2<br/>Seats: %3<br/>Max Speed: %4<br/><br/>%5", _capacity, _armor, _hasDriver, _maxSpeed, _text];

	(_this + ["CfgVehicles", _description]) call _getArticle;
};

_getUAV = {

	private ["_name","_capacity","_armor","_maxSpeed","_text","_description"];

	_name	        = [_this, 1] call BIS_fnc_param;
	_capacity	    = getNumber	(configFile >> "CfgVehicles" >> _name >> "maximumLoad");
	_armor		    = getNumber	(configFile >> "CfgVehicles" >> _name >> "armor");
	_maxSpeed	    = getNumber	(configFile >> "CfgVehicles" >> _name >> "maxSpeed");
	_text		    = getText	(configFile >> "CfgVehicles" >> _name >> "Armory" >> "description");
	_description	= format["Capacity: %1<br/>Armor: %2<br/>Max Speed: %3<br/><br/>%4", _capacity, _armor, _maxSpeed, _text];

	(_this + ["CfgVehicles", _description]) call _getArticle;
};